package pt.ua.ibank.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    }

    @Test
    void addNewComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    public void testGetCommentListByNumber() {
        // ArrayList<ModeratorComment> comments = CommentsDAO.getCommentListByNumber("3");
        assertNotNull(CommentsDAO.getCommentListByNumber("3"));
    }

    @Test
    public void testAddNewComment() {
        assertTrue(CommentsDAO.addNewComment("3", "Comentário de teste"));
    }

    @Test
    public void testDeleteComment() {
        assertTrue(CommentsDAO.deleteComment(1));
    }
}