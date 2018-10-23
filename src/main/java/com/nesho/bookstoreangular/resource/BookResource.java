package com.nesho.bookstoreangular.resource;

import com.nesho.bookstoreangular.domain.Book;
import com.nesho.bookstoreangular.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/book")
public class BookResource {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeBook(@RequestBody String id) {
        bookService.removeOne(Long.parseLong(id));
        return new ResponseEntity("Book Deleted", HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public Optional<Book> getBook(@PathVariable("id") String id) {
        return bookService.findOne(Long.parseLong(id));
    }

    @RequestMapping("/book-list")
    public List<Book> getAllBook() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Book addnewBook(@RequestBody Book book) {
        return bookService.save(book);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @RequestMapping(value = "/add/image", method = RequestMethod.POST)
    public ResponseEntity upload(
            @RequestParam("id") Long id,
            HttpServletResponse response, HttpServletRequest request
    ) {
        try {
            Optional<Book> book = bookService.findOne(id);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";


            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
            stream.write(bytes);
            stream.close();

            return new ResponseEntity("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/searchBook", method = RequestMethod.POST)
    public Set<Book> searchBook(@RequestBody String keyword) {
        Set<Book> bookList = bookService.blurrySearch(keyword);

        return bookList;
    }


}
