package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class termEnd2 extends termEnd {
  public termEnd2(ASTStringNode divide, factor factor1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("divide", divide),
      new PropertyOne<factor>("factor1", factor1)
    }, firstToken, lastToken);
  }
  public termEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new termEnd2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDivide() {
    return ((PropertyOne<ASTStringNode>)getProperty("divide")).getValue();
  }
  public factor getFactor1() {
    return ((PropertyOne<factor>)getProperty("factor1")).getValue();
  }
}
