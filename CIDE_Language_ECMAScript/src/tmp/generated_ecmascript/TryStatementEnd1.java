package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TryStatementEnd1 extends TryStatementEnd {
  public TryStatementEnd1(Finally finally_KW, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Finally>("finally_KW", finally_KW)
    }, firstToken, lastToken);
  }
  public TryStatementEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TryStatementEnd1(cloneProperties(),firstToken,lastToken);
  }
  public Finally getFinally_KW() {
    return ((PropertyOne<Finally>)getProperty("finally_KW")).getValue();
  }
}
