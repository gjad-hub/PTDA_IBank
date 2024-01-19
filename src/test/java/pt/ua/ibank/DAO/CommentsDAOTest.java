package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.ModeratorComment;

class CommentsDAOTest {

    @Test
    void testAddNewComment() {
        assertTrue(CommentsDAO.addNewComment(1, 1, "Teste"));
    }

    @Test
    void getCommentListByNumber() {
        ArrayList<ModeratorComment> list = CommentsDAO.getCommentListByID(1);
        assertNotNull(list);
    }

    @Test
    void testDeleteComment() {
        assertTrue(CommentsDAO.deleteComment(
                CommentsDAO.getCommentListByID(1).size() + 1));
    }

}
