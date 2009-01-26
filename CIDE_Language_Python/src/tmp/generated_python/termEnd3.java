package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class termEnd3 extends termEnd {
  public termEnd3(ASTStringNode floordivide, factor factor2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("floordivide", floordivide),
      new PropertyOne<factor>("factor2", factor2)
    }, firstToken, lastToken);
  }
  public termEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new termEnd3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloordivide() {
    return ((PropertyOne<ASTStringNode>)getProperty("floordivide")).getValue();
  }
  public factor getFactor2() {
    return ((PropertyOne<factor>)getProperty("factor2")).getValue();
  }
}
