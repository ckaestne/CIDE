package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TimesOrDivide1 extends TimesOrDivide {
  public TimesOrDivide1(TimesExpressionRest timesExpressionRest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TimesExpressionRest>("timesExpressionRest", timesExpressionRest)
    }, firstToken, lastToken);
  }
  public TimesOrDivide1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TimesOrDivide1(cloneProperties(),firstToken,lastToken);
  }
  public TimesExpressionRest getTimesExpressionRest() {
    return ((PropertyOne<TimesExpressionRest>)getProperty("timesExpressionRest")).getValue();
  }
}
