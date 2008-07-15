package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class valdef extends GenASTNode {
  public valdef(funlhs funlhs, declrhs declrhs, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<funlhs>("funlhs", funlhs),
      new PropertyOne<declrhs>("declrhs", declrhs)
    }, firstToken, lastToken);
  }
  public valdef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new valdef(cloneProperties(),firstToken,lastToken);
  }
  public funlhs getFunlhs() {
    return ((PropertyOne<funlhs>)getProperty("funlhs")).getValue();
  }
  public declrhs getDeclrhs() {
    return ((PropertyOne<declrhs>)getProperty("declrhs")).getValue();
  }
}
