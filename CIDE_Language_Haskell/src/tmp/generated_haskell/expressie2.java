package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie2 extends expressie {
  public expressie2(var var, contrExprParam contrExprParam, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyZeroOrOne<contrExprParam>("contrExprParam", contrExprParam)
    }, firstToken, lastToken);
  }
  public expressie2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressie2(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public contrExprParam getContrExprParam() {
    return ((PropertyZeroOrOne<contrExprParam>)getProperty("contrExprParam")).getValue();
  }
}
