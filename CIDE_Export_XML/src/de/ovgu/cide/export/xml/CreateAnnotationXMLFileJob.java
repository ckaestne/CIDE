package de.ovgu.cide.export.xml;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cide.gparser.ParseException;
import de.ovgu.cide.editor.CodeSegment;
import de.ovgu.cide.editor.CodeSegmentCalculator;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.DirectoryColorManager;

public class CreateAnnotationXMLFileJob extends Job {

	private IProject project;

	public CreateAnnotationXMLFileJob(IProject sourceProject) {
		super("Create Annotation XML File");
		this.project = sourceProject;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		assert project.isOpen();
		IStatus status = Status.OK_STATUS;
		try {
			IFeatureModel featureModel = FeatureModelManager.getInstance()
					.getFeatureModel(project);

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document dom = builder.newDocument();

			status = parseProject(project, dom, featureModel, monitor);

			StringWriter writer = new StringWriter();
			DOMSource domSource = new DOMSource(dom);
			StreamResult streamResult = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
					"annotations.dtd");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.transform(domSource, streamResult);

			IFile file = project.getFile("annotations.xml");
			if (file.exists())
				file.setContents(new ByteArrayInputStream(writer.toString()
						.getBytes()), true, true, monitor);
			else
				file.create(new ByteArrayInputStream(writer.toString()
						.getBytes()), true, monitor);

			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(IStatus.ERROR, "de.ovgu.cide.export.xml",
					"exception: " + e.getMessage());
		}
	}

	private IStatus parseProject(IProject project2, Document dom,
			IFeatureModel featureModel, IProgressMonitor monitor)
			throws CoreException, FeatureModelNotFoundException, ParseException {
		Element projectNode = dom.createElement("project");
		projectNode.setAttribute("name", project2.getName());
		dom.appendChild(projectNode);

		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManager(project2, featureModel);
		Set<IFeature> colors = dirColorManager.getFolderColors();
		addAnnotations(dom, projectNode, colors);

		for (IResource member : project2.members()) {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			if (member instanceof IFile)
				parseFile((IFile) member, projectNode, dom, featureModel,
						monitor);
			if (member instanceof IFolder)
				parseFolder((IFolder) member, projectNode, dom, featureModel,
						monitor);
		}
		return Status.OK_STATUS;
	}

	private IStatus parseFolder(IFolder folder, Element parentNode,
			Document dom, IFeatureModel featureModel, IProgressMonitor monitor)
			throws CoreException, FeatureModelNotFoundException, ParseException {
		Element folderNode = dom.createElement("folder");
		folderNode.setAttribute("name", folder.getName());
		parentNode.appendChild(folderNode);

		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManager(folder, featureModel);
		Set<IFeature> colors = dirColorManager.getFolderColors();
		addAnnotations(dom, folderNode, colors);

		for (IResource member : folder.members()) {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			if (member instanceof IFile)
				parseFile((IFile) member, folderNode, dom, featureModel,
						monitor);
			if (member instanceof IFolder)
				parseFolder((IFolder) member, folderNode, dom, featureModel,
						monitor);
		}
		return Status.OK_STATUS;
	}

	private IStatus parseFile(IFile file, Element parentNode, Document dom,
			IFeatureModel featureModel, IProgressMonitor monitor)
			throws FeatureModelNotFoundException, CoreException, ParseException {
		if ("color".equals(file.getFileExtension()))
			return Status.OK_STATUS;

		monitor.subTask("Parsing " + file.getName());

		Element fileNode = dom.createElement("file");
		fileNode.setAttribute("name", file.getName());
		parentNode.appendChild(fileNode);

		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(file.getParent(), featureModel);
		Set<IFeature> colors = dirColorManager.getColors(file);
		addAnnotations(dom, fileNode, colors);

		try {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			parseFileContents(file, fileNode, dom, featureModel);
		} catch (AssertionFailedException e) {
			e.printStackTrace();
		}
		return Status.OK_STATUS;
	}

	private void parseFileContents(IFile file, Element parentNode,
			Document dom, IFeatureModel featureModel)
			throws FeatureModelNotFoundException, CoreException, ParseException {

		ColoredSourceFile source = ColoredSourceFile.getColoredSourceFile(file);
		if (!source.isColored())
			return;

		List<CodeSegment> segments = CodeSegmentCalculator.getCodeSegments(
				source.getAST(), source.getColorManager());

		for (CodeSegment segment : segments)
			if (!segment.getColors().isEmpty()) {
				Element fragmentNode = dom.createElement("fragment");
				fragmentNode.setAttribute("offset", "" + segment.getOffset());
				fragmentNode.setAttribute("length", "" + segment.getLength());
				addAnnotations(dom, fragmentNode, segment.getColors());
				parentNode.appendChild(fragmentNode);
			}

	}

	private void addAnnotations(Document dom, Element parentNode,
			Set<IFeature> colors) {
		for (IFeature color : colors) {
			Element featureNode = dom.createElement("feature");
			featureNode.appendChild(dom.createTextNode(color.getName()));
			parentNode.appendChild(featureNode);
		}
	}

}