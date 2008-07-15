package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PlusOrMinus1 extends PlusOrMinus {
  public PlusOrMinus1(PlusExpressionRest plusExpressionRest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PlusExpressionRest>("plusExpressionRest", plusExpressionRest)
    }, firstToken, lastToken);
  }
  public PlusOrMinus1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PlusOrMinus1(cloneProperties(),firstToken,lastToken);
  }
  public PlusExpressionRest getPlusExpressionRest() {
    return ((PropertyOne<PlusExpressionRest>)getProperty("plusExpressionRest")).getValue();
  }
}
