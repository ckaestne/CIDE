package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class factor2 extends factor {
  public factor2(ASTStringNode minus, factor factor1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("minus", minus),
      new PropertyOne<factor>("factor1", factor1)
    }, firstToken, lastToken);
  }
  public factor2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new factor2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getMinus() {
    return ((PropertyOne<ASTStringNode>)getProperty("minus")).getValue();
  }
  public factor getFactor1() {
    return ((PropertyOne<factor>)getProperty("factor1")).getValue();
  }
}
