package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PPDefineStatement2 extends PPDefineStatement {
  public PPDefineStatement2(ASTStringNode findlineend1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findlineend1", findlineend1)
    }, firstToken, lastToken);
  }
  public PPDefineStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PPDefineStatement2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindlineend1() {
    return ((PropertyOne<ASTStringNode>)getProperty("findlineend1")).getValue();
  }
}
