package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeParameter extends GenASTNode {
  public TypeParameter(JavaIdentifier javaIdentifier, TypeBound typeBound, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyZeroOrOne<TypeBound>("typeBound", typeBound)
    }, firstToken, lastToken);
  }
  public TypeParameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeParameter(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public TypeBound getTypeBound() {
    return ((PropertyZeroOrOne<TypeBound>)getProperty("typeBound")).getValue();
  }
}
