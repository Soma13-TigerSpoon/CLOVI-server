package Soma.CLOVI.domain.user;

public enum RoleName {
  Y_CREATOR("유튜브 크리에이터"), ADMIN("관리자");
  private String name;

  RoleName(String name) {
    this.name = name;
  }
}
