package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_body extends GenASTNode {
  public class_body(ArrayList<class_member_declaration> class_member_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<class_member_declaration>("class_member_declaration", class_member_declaration)
    }, firstToken, lastToken);
  }
  public class_body(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_body(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<class_member_declaration> getClass_member_declaration() {
    return ((PropertyZeroOrMore<class_member_declaration>)getProperty("class_member_declaration")).getValue();
  }
}
