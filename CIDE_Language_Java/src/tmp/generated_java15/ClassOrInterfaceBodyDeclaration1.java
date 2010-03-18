package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBodyDeclaration1 extends ClassOrInterfaceBodyDeclaration {
  public ClassOrInterfaceBodyDeclaration1(Initializer initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Initializer>("initializer", initializer)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBodyDeclaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBodyDeclaration1(cloneProperties(),firstToken,lastToken);
  }
  public Initializer getInitializer() {
    return ((PropertyOne<Initializer>)getProperty("initializer")).getValue();
  }
}
