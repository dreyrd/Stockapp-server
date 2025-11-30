package br.com.ifsp.StockApp.model.user;

public record UserDataResponse(
        Integer userId,
        String name,
        String email,
        Boolean enable
) {
    public UserDataResponse(User user) {
        this(user.getUserId(), user.getName(), user.getEmail(), user.getEnable());
    }
}