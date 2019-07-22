package lits.jp.hotel.management.services;

public interface TokenService {
    Long parseToken(String token);
    String createToken(Long id);
}
