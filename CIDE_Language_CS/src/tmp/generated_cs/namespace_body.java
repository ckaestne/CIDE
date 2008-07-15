package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class namespace_body extends GenASTNode {
  public namespace_body(ArrayList<using_directive> using_directive, ArrayList<namespace_member_declaration> namespace_member_declaration, ASTTextNode text210, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<using_directive>("using_directive", using_directive),
      new PropertyZeroOrMore<namespace_member_declaration>("namespace_member_declaration", namespace_member_declaration),
      new PropertyZeroOrOne<ASTTextNode>("text210", text210)
    }, firstToken, lastToken);
  }
  public namespace_body(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new namespace_body(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<using_directive> getUsing_directive() {
    return ((PropertyZeroOrMore<using_directive>)getProperty("using_directive")).getValue();
  }
  public ArrayList<namespace_member_declaration> getNamespace_member_declaration() {
    return ((PropertyZeroOrMore<namespace_member_declaration>)getProperty("namespace_member_declaration")).getValue();
  }
  public ASTTextNode getText210() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text210")).getValue();
  }
}
