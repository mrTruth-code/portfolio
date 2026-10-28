package com.portfolio.controller;

import com.portfolio.model.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PortfolioController {

    // ─── Home / Index ────────────────────────────────────────────────────────

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("skills", getSkills());
        model.addAttribute("projects", getProjects());
        model.addAttribute("experiences", getExperiences());
        model.addAttribute("certifications", getCertifications());
        model.addAttribute("contactForm", new ContactForm());
        return "index";
    }

    // ─── Contact Form POST ────────────────────────────────────────────────────

    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contactForm") ContactForm form,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("skills", getSkills());
            model.addAttribute("projects", getProjects());
            model.addAttribute("experiences", getExperiences());
            model.addAttribute("certifications", getCertifications());
            model.addAttribute("contactError", true);
            return "index";
        }

        System.out.println("📧 New message from: " + form.getName() + " <" + form.getEmail() + ">");
        System.out.println("   Subject: " + form.getSubject());
        System.out.println("   Message: " + form.getMessage());

        redirectAttributes.addFlashAttribute("contactSuccess", true);
        return "redirect:/#contact";
    }

    // ─── Data Providers ──────────────────────────────────────────────────────

    private List<Skill> getSkills() {
        return List.of(
            new Skill("Java", 92, "☕", "#f89820"),
            new Skill("C# & ASP.NET Core", 88, "🔷", "#178600"),
            new Skill("Python", 85, "🐍", "#3776ab"),
            new Skill("JavaScript & TypeScript", 82, "⚡", "#f7df1e"),
            new Skill("SQL Server & PL/SQL", 88, "🗄️", "#00758f"),
            new Skill("MySQL & Oracle", 85, "📊", "#00758f"),
            new Skill("Docker & Linux", 75, "🐳", "#2496ed"),
            new Skill("Azure AI & Cloud Services", 78, "☁️", "#ff9900")
        );
    }

    private List<Project> getProjects() {
        return List.of(
            new Project(
                "Attendance App",
                "Developed a modern, real-time attendance tracking web application enabling instant student check-ins via dynamic QR codes. Built a secure Admin Dashboard to generate, monitor, and export weekly CSV attendance logs. Integrated HTML5-qrcode to engineer a custom optical matrix scanner with live camera feeds and haptic feedback. Designed a highly resilient dual-storage backend architecture that utilizes Vercel KV (Redis) as the primary cloud database, falling back to atomic local file-system writes during network timeouts to help guarantee zero data loss. Crafted a premium, responsive UI/UX featuring fluid micro-animations, glassmorphism elements, and modular components.",
                "/images/attendance.jpg",
                "https://github.com/mrTruth-code",
                "https://example.com",
                "Full-Stack",
                "Vercel KV", "Redis", "HTML5-qrcode", "JavaScript", "CSS3"
            ),
            new Project(
                "Enterprise CRUD Portal",
                "A robust management system built using ASP.NET Core MVC and SQL Server, incorporating role-based access, real-time logging, transactional integrity, and automated CSV backups.",
                "/images/enterprise.jpg",
                "https://github.com/mrTruth-code",
                "https://example.com",
                "Backend",
                "C#", "ASP.NET Core", "MVC", "SQL Server", "Bootstrap"
            ),
            new Project(
                "Azure Intelligent Document Assistant",
                "An AI assistant leveraging Azure OpenAI services to extract, index, and query semantic intelligence from unstructured corporate documents with zero data leakage.",
                "/images/ai_doc.jpg",
                "https://github.com/mrTruth-code",
                "https://example.com",
                "AI / ML",
                "Python", "Azure OpenAI", "Azure Search", "REST APIs"
            )
        );
    }

    private List<Experience> getExperiences() {
        return List.of(
            new Experience(
                "Dynamic DNA",
                "Microsoft AI Cloud Developer Programme Participant",
                "1 Aug 2026 – 30 Nov 2026",
                "Randburg, South Africa",
                null,
                "Participating in a Microsoft AI Cloud Developer programme focused on cloud computing, Microsoft Azure, and artificial intelligence.",
                "Developing knowledge of Azure cloud services and AI development concepts through programme-related learning activities.",
                "Preparing for Microsoft Azure Fundamentals (AZ-900) and AI-200 certification examinations as outlined in the programme.",
                "Strengthening technical skills in cloud technologies and AI to support future software development opportunities."
            ),
            new Experience(
                "Phosaane Training Institute",
                "Software Development Facilitator / SME (Freelance)",
                "Jul 2025 – Jun 2026",
                "Ruimsig, South Africa",
                "Delivered software development training aligned with SAQA/QCTO standards, mentored learners in Python, SQL, and software engineering, and assessed capstone projects.",
                "Delivered a software development training curriculum to 11+ learners, achieving 100% compliance with SAQA/QCTO national standards.",
                "Mentored 11+ learners across Python, SQL, and software engineering principles, with 50+% completing their capstone projects.",
                "Assessed 9+ software projects and delivered 1-on-1 technical coaching sessions, improving code quality and project outcomes.",
                "Prepared quarterly progress reports for 6+ learners and maintained 75% audit-ready documentation standards for quality assurance."
            ),
            new Experience(
                "Department of Home Affairs",
                "IT Intern / Software Developer Intern",
                "Sep 2024 – Aug 2025",
                "Pretoria, South Africa",
                "Developed, tested, and maintained software applications in a government environment, supported application deployment, and managed SQL Server databases.",
                "Developed, tested, and maintained 5+ software applications using ASP.NET Core, C#, and SQL Server.",
                "Supported the deployment and troubleshooting of applications used by 50+ users.",
                "Worked with 3–5 SQL Server databases for application development, testing, and data management.",
                "Collaborated with a cross-functional team of 5–10 members, including developers, testers, and supervisors.",
                "Resolved 20–40+ software defects and implemented enhancements based on user feedback.",
                "Produced and maintained 10–20 technical documents, including test cases, reports, and system documentation."
            ),
            new Experience(
                "Netcampus Group",
                "IT Skills Programme Participant — Labor Activation Programme",
                "26 Feb 2024 – 26 Feb 2025",
                "South Africa",
                null,
                "Registered for a 12-month Labor Activation Programme sponsored by the Department of Labour, with Netcampus Group as the implementation partner.",
                "Participated in a programme structured around technical training, practical exercises, and hands-on IT skills development.",
                "Programme activities included developing a small application, participating in a three-month project simulation, and work-integrated training.",
                "Gained exposure to workplace-oriented IT skills and practical software development activities."
            )
        );
    }

    private List<Certification> getCertifications() {
        return List.of(
            new Certification(
                "Microsoft Certified: Azure AI Engineer Associate",
                "Netcampus Group",
                "April 04, 2024 – April 05, 2027",
                "🤖"
            ),
            new Certification(
                "Microsoft Certified: Azure Data Fundamentals",
                "Netcampus Group",
                "Completed: March 13, 2024",
                "📊"
            ),
            new Certification(
                "GenAI for Software Engineers",
                "WeThinkCode",
                "Completed: May 01, 2026",
                "💻"
            ),
            new Certification(
                "OIAI AI Literacy Skills",
                "Otermans Institute",
                "Completed: April 30, 2026",
                "🧠"
            ),
            new Certification(
                "Introduction to Cybersecurity",
                "Tshwane University of Technology",
                "Completed: November 14, 2022",
                "🔐"
            ),
            new Certification(
                "Microsoft Certified: Azure Fundamentals",
                "DYNAMICDNA",
                "Completed: September 10, 2026",
                "☁️"
            )
        );
    }
}
