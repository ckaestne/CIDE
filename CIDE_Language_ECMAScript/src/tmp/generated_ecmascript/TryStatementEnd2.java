package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TryStatementEnd2 extends TryStatementEnd {
  public TryStatementEnd2(Catch catch_KW, Finally finally_KW1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Catch>("catch_KW", catch_KW),
      new PropertyZeroOrOne<Finally>("finally_KW1", finally_KW1)
    }, firstToken, lastToken);
  }
  public TryStatementEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TryStatementEnd2(cloneProperties(),firstToken,lastToken);
  }
  public Catch getCatch_KW() {
    return ((PropertyOne<Catch>)getProperty("catch_KW")).getValue();
  }
  public Finally getFinally_KW1() {
    return ((PropertyZeroOrOne<Finally>)getProperty("finally_KW1")).getValue();
  }
}
