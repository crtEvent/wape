package crtevn.webapp.repository;

import crtevn.webapp.model.Member;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

public class MemberRepository {

    private static MemberRepository instance;

    private final Map<String, Member> repository = new ConcurrentSkipListMap<>();

    private MemberRepository() {}

    public static synchronized MemberRepository initialize() {
        if (instance == null) {
            instance = new MemberRepository();
        } else {
            throw new IllegalStateException("MemberRepository is already initialized.");
        }
        return instance;
    }

    public static MemberRepository getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                "MemberRepository is not initialized yet. Please call initialize() first.");
        }
        return instance;
    }

    public Optional<Member> findById(String email) {
        return Optional.ofNullable(repository.get(email));
    }

    public void save(Member member) {
        if (repository.containsKey(member.getEmail())) {
            repository.replace(member.getEmail(), member);
        } else {
            repository.put(member.getEmail(), member);
        }
    }

}
