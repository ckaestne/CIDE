package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceTypeIntern extends GenASTNode {
  public ClassOrInterfaceTypeIntern(JavaIdentifier javaIdentifier, TypeArguments typeArguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyZeroOrOne<TypeArguments>("typeArguments", typeArguments)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceTypeIntern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceTypeIntern(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public TypeArguments getTypeArguments() {
    return ((PropertyZeroOrOne<TypeArguments>)getProperty("typeArguments")).getValue();
  }
}
