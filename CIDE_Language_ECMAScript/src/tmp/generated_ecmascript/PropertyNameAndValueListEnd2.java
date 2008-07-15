package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PropertyNameAndValueListEnd2 extends PropertyNameAndValueListEnd {
  public PropertyNameAndValueListEnd2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public PropertyNameAndValueListEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PropertyNameAndValueListEnd2(cloneProperties(),firstToken,lastToken);
  }
}
