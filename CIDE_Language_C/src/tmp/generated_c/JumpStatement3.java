package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JumpStatement3 extends JumpStatement {
  public JumpStatement3(ASTStringNode break_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("break_kw", break_kw)
    }, firstToken, lastToken);
  }
  public JumpStatement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JumpStatement3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getBreak_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("break_kw")).getValue();
  }
}
