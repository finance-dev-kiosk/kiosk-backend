package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.repository.jpa.CommentRepository;
import org.springframework.stereotype.Service;

@TypeInfo(name = "CommentService", description = "댓글 서비스 클래스")
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
