package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CallExpressionPart3 extends CallExpressionPart {
  public CallExpressionPart3(Identifier identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public CallExpressionPart3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CallExpressionPart3(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
}
