package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBodyDeclaration4 extends ClassOrInterfaceBodyDeclaration {
  public ClassOrInterfaceBodyDeclaration4(Modifiers modifiers2, ConstructorDeclaration constructorDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers2", modifiers2),
      new PropertyOne<ConstructorDeclaration>("constructorDeclaration", constructorDeclaration)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBodyDeclaration4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBodyDeclaration4(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers2() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers2")).getValue();
  }
  public ConstructorDeclaration getConstructorDeclaration() {
    return ((PropertyOne<ConstructorDeclaration>)getProperty("constructorDeclaration")).getValue();
  }
}
