package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForInit1 extends ForInit {
  public ForInit1(LocalVariableDeclaration localVariableDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LocalVariableDeclaration>("localVariableDeclaration", localVariableDeclaration)
    }, firstToken, lastToken);
  }
  public ForInit1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForInit1(cloneProperties(),firstToken,lastToken);
  }
  public LocalVariableDeclaration getLocalVariableDeclaration() {
    return ((PropertyOne<LocalVariableDeclaration>)getProperty("localVariableDeclaration")).getValue();
  }
}
