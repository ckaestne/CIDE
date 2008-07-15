package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TimesOrDivide2 extends TimesOrDivide {
  public TimesOrDivide2(DivideExpressionRest divideExpressionRest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DivideExpressionRest>("divideExpressionRest", divideExpressionRest)
    }, firstToken, lastToken);
  }
  public TimesOrDivide2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TimesOrDivide2(cloneProperties(),firstToken,lastToken);
  }
  public DivideExpressionRest getDivideExpressionRest() {
    return ((PropertyOne<DivideExpressionRest>)getProperty("divideExpressionRest")).getValue();
  }
}
