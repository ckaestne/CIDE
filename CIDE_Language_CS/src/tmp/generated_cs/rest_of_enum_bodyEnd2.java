package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class rest_of_enum_bodyEnd2 extends rest_of_enum_bodyEnd {
  public rest_of_enum_bodyEnd2(rest_of_enum_body rest_of_enum_body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<rest_of_enum_body>("rest_of_enum_body", rest_of_enum_body)
    }, firstToken, lastToken);
  }
  public rest_of_enum_bodyEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new rest_of_enum_bodyEnd2(cloneProperties(),firstToken,lastToken);
  }
  public rest_of_enum_body getRest_of_enum_body() {
    return ((PropertyOne<rest_of_enum_body>)getProperty("rest_of_enum_body")).getValue();
  }
}
