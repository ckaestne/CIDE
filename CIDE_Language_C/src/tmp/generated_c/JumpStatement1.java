package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JumpStatement1 extends JumpStatement {
  public JumpStatement1(ASTStringNode goto_kw, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("goto_kw", goto_kw),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public JumpStatement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JumpStatement1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getGoto_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("goto_kw")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
