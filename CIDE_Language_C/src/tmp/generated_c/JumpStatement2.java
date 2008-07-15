package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JumpStatement2 extends JumpStatement {
  public JumpStatement2(ASTStringNode continue_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("continue_kw", continue_kw)
    }, firstToken, lastToken);
  }
  public JumpStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JumpStatement2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getContinue_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("continue_kw")).getValue();
  }
}
