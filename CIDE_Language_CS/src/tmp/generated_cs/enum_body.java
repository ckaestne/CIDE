package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class enum_body extends GenASTNode {
  public enum_body(rest_of_enum_body rest_of_enum_body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<rest_of_enum_body>("rest_of_enum_body", rest_of_enum_body)
    }, firstToken, lastToken);
  }
  public enum_body(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new enum_body(cloneProperties(),firstToken,lastToken);
  }
  public rest_of_enum_body getRest_of_enum_body() {
    return ((PropertyOne<rest_of_enum_body>)getProperty("rest_of_enum_body")).getValue();
  }
}
