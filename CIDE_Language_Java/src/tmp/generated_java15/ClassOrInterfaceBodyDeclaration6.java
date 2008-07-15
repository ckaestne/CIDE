package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBodyDeclaration6 extends ClassOrInterfaceBodyDeclaration {
  public ClassOrInterfaceBodyDeclaration6(Modifiers modifiers4, MethodDeclaration methodDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers4", modifiers4),
      new PropertyOne<MethodDeclaration>("methodDeclaration", methodDeclaration)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBodyDeclaration6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBodyDeclaration6(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers4() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers4")).getValue();
  }
  public MethodDeclaration getMethodDeclaration() {
    return ((PropertyOne<MethodDeclaration>)getProperty("methodDeclaration")).getValue();
  }
}
