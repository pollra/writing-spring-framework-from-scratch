package wsffs.springframework.boot.web.servlet.handler.definition;

public class PathVariableDefinition {

  private final int index;
  private final Class type;
  private final String name;

  /**
   * path 사이에 존재 할 수 있는 Path variable 정보를 지정한다.
   *
   * @param index start number is 1. (/api/{number} : 2)
   * @param type path variable type
   * @param name path variable name
   */
  public PathVariableDefinition(int index, Class type, String name) {
    this.index = index;
    this.type = type;
    this.name = name;
  }

  @Override
  public String toString() {
    return "{t(" + type.getName() + "):n(" + name + ")}";
  }
}
