package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class termEnd4 extends termEnd {
  public termEnd4(ASTStringNode modulo, factor factor3, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("modulo", modulo),
      new PropertyOne<factor>("factor3", factor3)
    }, firstToken, lastToken);
  }
  public termEnd4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new termEnd4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getModulo() {
    return ((PropertyOne<ASTStringNode>)getProperty("modulo")).getValue();
  }
  public factor getFactor3() {
    return ((PropertyOne<factor>)getProperty("factor3")).getValue();
  }
}
