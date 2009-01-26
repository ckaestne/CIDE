package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class raise_stmt extends GenASTNode {
  public raise_stmt(ASTStringNode raise, raise_stmt_end raise_stmt_end, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("raise", raise),
      new PropertyZeroOrOne<raise_stmt_end>("raise_stmt_end", raise_stmt_end)
    }, firstToken, lastToken);
  }
  public raise_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new raise_stmt(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRaise() {
    return ((PropertyOne<ASTStringNode>)getProperty("raise")).getValue();
  }
  public raise_stmt_end getRaise_stmt_end() {
    return ((PropertyZeroOrOne<raise_stmt_end>)getProperty("raise_stmt_end")).getValue();
  }
}
