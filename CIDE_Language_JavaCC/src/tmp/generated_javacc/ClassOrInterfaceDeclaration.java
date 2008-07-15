package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceDeclaration extends GenASTNode {
  public ClassOrInterfaceDeclaration(ClassOrInterface classOrInterface, JavaIdentifier javaIdentifier, TypeParameters typeParameters, ExtendsList extendsList, ImplementsList implementsList, ClassOrInterfaceBody classOrInterfaceBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ClassOrInterface>("classOrInterface", classOrInterface),
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyZeroOrOne<TypeParameters>("typeParameters", typeParameters),
      new PropertyZeroOrOne<ExtendsList>("extendsList", extendsList),
      new PropertyZeroOrOne<ImplementsList>("implementsList", implementsList),
      new PropertyOne<ClassOrInterfaceBody>("classOrInterfaceBody", classOrInterfaceBody)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ClassOrInterface getClassOrInterface() {
    return ((PropertyOne<ClassOrInterface>)getProperty("classOrInterface")).getValue();
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public TypeParameters getTypeParameters() {
    return ((PropertyZeroOrOne<TypeParameters>)getProperty("typeParameters")).getValue();
  }
  public ExtendsList getExtendsList() {
    return ((PropertyZeroOrOne<ExtendsList>)getProperty("extendsList")).getValue();
  }
  public ImplementsList getImplementsList() {
    return ((PropertyZeroOrOne<ImplementsList>)getProperty("implementsList")).getValue();
  }
  public ClassOrInterfaceBody getClassOrInterfaceBody() {
    return ((PropertyOne<ClassOrInterfaceBody>)getProperty("classOrInterfaceBody")).getValue();
  }
}
