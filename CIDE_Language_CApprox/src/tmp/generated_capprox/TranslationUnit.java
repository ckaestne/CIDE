package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TranslationUnit extends GenASTNode implements ISourceFile {
  public TranslationUnit(Sequence_CodeUnit_TopLevel sequence_CodeUnit_TopLevel, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Sequence_CodeUnit_TopLevel>("sequence_CodeUnit_TopLevel", sequence_CodeUnit_TopLevel),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public TranslationUnit(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TranslationUnit(cloneProperties(),firstToken,lastToken);
  }
  public Sequence_CodeUnit_TopLevel getSequence_CodeUnit_TopLevel() {
    return ((PropertyOne<Sequence_CodeUnit_TopLevel>)getProperty("sequence_CodeUnit_TopLevel")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
