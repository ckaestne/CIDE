package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class factor3 extends factor {
  public factor3(ASTStringNode not, factor factor2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("not", not),
      new PropertyOne<factor>("factor2", factor2)
    }, firstToken, lastToken);
  }
  public factor3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new factor3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNot() {
    return ((PropertyOne<ASTStringNode>)getProperty("not")).getValue();
  }
  public factor getFactor2() {
    return ((PropertyOne<factor>)getProperty("factor2")).getValue();
  }
}
