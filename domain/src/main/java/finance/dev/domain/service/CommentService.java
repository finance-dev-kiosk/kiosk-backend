package finance.dev.domain.service;

import finance.dev.common.annotation.TypeInfo;
import finance.dev.domain.entity.CommentEntity;
import finance.dev.domain.repository.jpa.CommentRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Service;

@TypeInfo(name = "CommentService", description = "댓글 서비스 클래스")
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final EntityManager entityManager;

    public List<CommentEntity> getStoreComments(Long storeId) {
        StringBuilder queryString =
                new StringBuilder("SELECT c FROM CommentEntity c WHERE c.storeIdx = ");
        queryString.append(storeId);

        return entityManager
                .createQuery(queryString.toString(), CommentEntity.class)
                .getResultList();
    }

    public void createStoreComment(Long storeId, Long userId, String comment) {
        CommentEntity commentEntity =
                CommentEntity.builder().storeIdx(storeId).userIdx(userId).content(comment).build();

        commentRepository.save(commentEntity);
    }

    public void updateStoreComment(Long storeId, Long commentId, Long userId, String comment) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();

        commentRepository.save(
                CommentEntity.builder()
                        .idx(commentId)
                        .storeIdx(storeId)
                        .userIdx(userId)
                        .content(comment)
                        .build());
    }

    public void deleteStoreComment(Long storeId, Long commentId, Long userId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();

        if (!commentEntity.getUserIdx().equals(userId)) {
            throw new IllegalStateException("댓글 삭제 권한이 없습니다.");
        }

        commentRepository.deleteById(commentId);
    }

    public CommentService(EntityManager entityManager, CommentRepository commentRepository) {
        this.entityManager = entityManager;
        this.commentRepository = commentRepository;
    }
}
