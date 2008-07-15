package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PPDefineStatement1 extends PPDefineStatement {
  public PPDefineStatement1(ASTStringNode findlineend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findlineend", findlineend)
    }, firstToken, lastToken);
  }
  public PPDefineStatement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PPDefineStatement1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindlineend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findlineend")).getValue();
  }
}
