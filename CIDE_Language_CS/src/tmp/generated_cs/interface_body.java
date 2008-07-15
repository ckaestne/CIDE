package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_body extends GenASTNode {
  public interface_body(ArrayList<interface_member_declaration> interface_member_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<interface_member_declaration>("interface_member_declaration", interface_member_declaration)
    }, firstToken, lastToken);
  }
  public interface_body(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_body(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<interface_member_declaration> getInterface_member_declaration() {
    return ((PropertyZeroOrMore<interface_member_declaration>)getProperty("interface_member_declaration")).getValue();
  }
}
