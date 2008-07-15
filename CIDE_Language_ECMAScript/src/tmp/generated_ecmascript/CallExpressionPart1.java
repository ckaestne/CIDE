package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CallExpressionPart1 extends CallExpressionPart {
  public CallExpressionPart1(Arguments arguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Arguments>("arguments", arguments)
    }, firstToken, lastToken);
  }
  public CallExpressionPart1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CallExpressionPart1(cloneProperties(),firstToken,lastToken);
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
}
