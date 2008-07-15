package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compilation_unitEnd extends GenASTNode {
  public compilation_unitEnd(namespace_member_declaration_no_attr namespace_member_declaration_no_attr, ArrayList<namespace_member_declaration> namespace_member_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<namespace_member_declaration_no_attr>("namespace_member_declaration_no_attr", namespace_member_declaration_no_attr),
      new PropertyZeroOrMore<namespace_member_declaration>("namespace_member_declaration", namespace_member_declaration)
    }, firstToken, lastToken);
  }
  public compilation_unitEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compilation_unitEnd(cloneProperties(),firstToken,lastToken);
  }
  public namespace_member_declaration_no_attr getNamespace_member_declaration_no_attr() {
    return ((PropertyOne<namespace_member_declaration_no_attr>)getProperty("namespace_member_declaration_no_attr")).getValue();
  }
  public ArrayList<namespace_member_declaration> getNamespace_member_declaration() {
    return ((PropertyZeroOrMore<namespace_member_declaration>)getProperty("namespace_member_declaration")).getValue();
  }
}
