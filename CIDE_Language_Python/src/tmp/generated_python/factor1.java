package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class factor1 extends factor {
  public factor1(ASTStringNode plus, factor factor, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("plus", plus),
      new PropertyOne<factor>("factor", factor)
    }, firstToken, lastToken);
  }
  public factor1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new factor1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPlus() {
    return ((PropertyOne<ASTStringNode>)getProperty("plus")).getValue();
  }
  public factor getFactor() {
    return ((PropertyOne<factor>)getProperty("factor")).getValue();
  }
}
