package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SourceElement1 extends SourceElement {
  public SourceElement1(FunctionDeclaration functionDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FunctionDeclaration>("functionDeclaration", functionDeclaration)
    }, firstToken, lastToken);
  }
  public SourceElement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SourceElement1(cloneProperties(),firstToken,lastToken);
  }
  public FunctionDeclaration getFunctionDeclaration() {
    return ((PropertyOne<FunctionDeclaration>)getProperty("functionDeclaration")).getValue();
  }
}
