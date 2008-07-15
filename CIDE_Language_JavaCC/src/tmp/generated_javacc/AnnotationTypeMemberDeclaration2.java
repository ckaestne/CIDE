package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeMemberDeclaration2 extends AnnotationTypeMemberDeclaration {
  public AnnotationTypeMemberDeclaration2(Modifiers modifiers1, ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers1", modifiers1),
      new PropertyOne<ClassOrInterfaceDeclaration>("classOrInterfaceDeclaration", classOrInterfaceDeclaration)
    }, firstToken, lastToken);
  }
  public AnnotationTypeMemberDeclaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeMemberDeclaration2(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers1() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers1")).getValue();
  }
  public ClassOrInterfaceDeclaration getClassOrInterfaceDeclaration() {
    return ((PropertyOne<ClassOrInterfaceDeclaration>)getProperty("classOrInterfaceDeclaration")).getValue();
  }
}
