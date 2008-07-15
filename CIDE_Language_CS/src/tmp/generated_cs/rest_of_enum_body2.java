package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class rest_of_enum_body2 extends rest_of_enum_body {
  public rest_of_enum_body2(enum_member_declaration enum_member_declaration, rest_of_enum_bodyEnd rest_of_enum_bodyEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<enum_member_declaration>("enum_member_declaration", enum_member_declaration),
      new PropertyOne<rest_of_enum_bodyEnd>("rest_of_enum_bodyEnd", rest_of_enum_bodyEnd)
    }, firstToken, lastToken);
  }
  public rest_of_enum_body2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new rest_of_enum_body2(cloneProperties(),firstToken,lastToken);
  }
  public enum_member_declaration getEnum_member_declaration() {
    return ((PropertyOne<enum_member_declaration>)getProperty("enum_member_declaration")).getValue();
  }
  public rest_of_enum_bodyEnd getRest_of_enum_bodyEnd() {
    return ((PropertyOne<rest_of_enum_bodyEnd>)getProperty("rest_of_enum_bodyEnd")).getValue();
  }
}
