package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockStatement3 extends BlockStatement {
  public BlockStatement3(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ClassOrInterfaceDeclaration>("classOrInterfaceDeclaration", classOrInterfaceDeclaration)
    }, firstToken, lastToken);
  }
  public BlockStatement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockStatement3(cloneProperties(),firstToken,lastToken);
  }
  public ClassOrInterfaceDeclaration getClassOrInterfaceDeclaration() {
    return ((PropertyOne<ClassOrInterfaceDeclaration>)getProperty("classOrInterfaceDeclaration")).getValue();
  }
}
