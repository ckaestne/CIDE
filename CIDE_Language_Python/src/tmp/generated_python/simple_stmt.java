package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class simple_stmt extends GenASTNode {
  public simple_stmt(small_stmt small_stmt, ArrayList<small_stmt> small_stmt1, ASTTextNode text580, ASTStringNode newline, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<small_stmt>("small_stmt", small_stmt),
      new PropertyZeroOrMore<small_stmt>("small_stmt1", small_stmt1),
      new PropertyZeroOrOne<ASTTextNode>("text580", text580),
      new PropertyOne<ASTStringNode>("newline", newline)
    }, firstToken, lastToken);
  }
  public simple_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new simple_stmt(cloneProperties(),firstToken,lastToken);
  }
  public small_stmt getSmall_stmt() {
    return ((PropertyOne<small_stmt>)getProperty("small_stmt")).getValue();
  }
  public ArrayList<small_stmt> getSmall_stmt1() {
    return ((PropertyZeroOrMore<small_stmt>)getProperty("small_stmt1")).getValue();
  }
  public ASTTextNode getText580() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text580")).getValue();
  }
  public ASTStringNode getNewline() {
    return ((PropertyOne<ASTStringNode>)getProperty("newline")).getValue();
  }
}
