package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class global_stmt extends GenASTNode {
  public global_stmt(ASTStringNode global, Name name, ArrayList<Name> name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("global", global),
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrMore<Name>("name1", name1)
    }, firstToken, lastToken);
  }
  public global_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new global_stmt(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getGlobal() {
    return ((PropertyOne<ASTStringNode>)getProperty("global")).getValue();
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ArrayList<Name> getName1() {
    return ((PropertyZeroOrMore<Name>)getProperty("name1")).getValue();
  }
}
