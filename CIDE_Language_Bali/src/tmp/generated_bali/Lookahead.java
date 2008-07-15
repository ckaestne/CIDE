package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Lookahead extends GenASTNode {
  public Lookahead(ASTStringNode _lookahead, ASTStringNode findcloseparen, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_lookahead", _lookahead),
      new PropertyOne<ASTStringNode>("findcloseparen", findcloseparen)
    }, firstToken, lastToken);
  }
  public Lookahead(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Lookahead(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_lookahead() {
    return ((PropertyOne<ASTStringNode>)getProperty("_lookahead")).getValue();
  }
  public ASTStringNode getFindcloseparen() {
    return ((PropertyOne<ASTStringNode>)getProperty("findcloseparen")).getValue();
  }
}
