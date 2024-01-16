package pt.ua.ibank.DAO;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test.*;
import pt.ua.ibank.DTO.ModeratorComment;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommentsDAOTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCommentListByNumber() {
        ArrayList<ModeratorComment> list = CommentsDAO.getCommentListByID(1);
        assertNotNull(list);
    }

    @Test
    void testAddNewComment() {
        assertTrue(CommentsDAO.addNewComment(1, 1,"Teste"));
    }

    @Test
    void testDeleteComment() {
        assertTrue(CommentsDAO.deleteComment(1));
    }
}